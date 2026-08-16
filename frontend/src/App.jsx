import { useState } from "react";
import "./App.css";

function App() {
  const [candidateName, setCandidateName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [resumeFile, setResumeFile] = useState(null);

  const [companyName, setCompanyName] = useState("");
  const [jobTitle, setJobTitle] = useState("");
  const [jobDescription, setJobDescription] = useState("");

  const [loading, setLoading] = useState(false);
  const [analysisResult, setAnalysisResult] = useState(null);
  const [error, setError] = useState("");

  const [fileInputKey, setFileInputKey] = useState(0);

  const handleAnalyze = async (e) => {
    e.preventDefault();

    if (!resumeFile) {
      alert("Please upload your resume PDF.");
      return;
    }

    if (!candidateName || !email || !phone) {
      alert("Please fill in all candidate details.");
      return;
    }

    if (!companyName || !jobTitle || !jobDescription) {
      alert("Please fill in all job details.");
      return;
    }

    setLoading(true);
    setError("");
    setAnalysisResult(null);

    try {
      // 1. Upload Resume
      const resumeData = new FormData();

      resumeData.append("file", resumeFile);
      resumeData.append("candidateName", candidateName);
      resumeData.append("email", email);
      resumeData.append("phone", phone);

      const resumeResponse = await fetch(
          "http://localhost:8080/api/resumes/upload",
          {
            method: "POST",
            body: resumeData,
          }
      );

      if (!resumeResponse.ok) {
        throw new Error("Resume upload failed.");
      }

      const resume = await resumeResponse.json();

      console.log("Resume uploaded:", resume);

      // 2. Create Job
      const jobResponse = await fetch(
          "http://localhost:8080/api/jobs",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              companyName: companyName,
              jobTitle: jobTitle,
              description: jobDescription,
            }),
          }
      );

      if (!jobResponse.ok) {
        throw new Error("Job creation failed.");
      }

      const job = await jobResponse.json();

      console.log("Job created:", job);

      const resumeId = resume.id;
      const jobId = job.id;

      // 3. Matching
      const matchResponse = await fetch(
          `http://localhost:8080/api/matching?resumeId=${resumeId}&jobId=${jobId}`
      );

      if (!matchResponse.ok) {
        throw new Error("Match analysis failed.");
      }

      const matchResult = await matchResponse.json();

      // 4. Skill Gap
      const skillGapResponse = await fetch(
          `http://localhost:8080/api/skill-gap?resumeId=${resumeId}&jobId=${jobId}`
      );

      if (!skillGapResponse.ok) {
        throw new Error("Skill gap analysis failed.");
      }

      const skillGap = await skillGapResponse.json();

      // 5. ATS Score
      const atsResponse = await fetch(
          `http://localhost:8080/api/ats?resumeId=${resumeId}&jobId=${jobId}`
      );

      if (!atsResponse.ok) {
        throw new Error("ATS score calculation failed.");
      }

      const atsResult = await atsResponse.json();

      // 6. Recommendations
      const recommendationResponse = await fetch(
          `http://localhost:8080/api/recommendations?resumeId=${resumeId}&jobId=${jobId}`
      );

      if (!recommendationResponse.ok) {
        throw new Error("Recommendation generation failed.");
      }

      const recommendations = await recommendationResponse.json();

      // 7. Store complete analysis
      setAnalysisResult({
        resumeId,
        jobId,
        matchResult,
        skillGap,
        atsResult,
        recommendations,
      });

    }  catch (error) {
    console.error("Analysis error:", error);

    if (error.message === "Failed to fetch") {
      setError(
          "Unable to connect to the Resume Analyzer server. Please make sure the backend is running and try again."
      );
    } else {
      setError(
          "Something went wrong while analyzing your resume. Please try again."
      );
    }
  } finally {
      setLoading(false);
    }
  };

  const handleNewAnalysis = () => {
    setAnalysisResult(null);
    setError("");

    setCandidateName("");
    setEmail("");
    setPhone("");
    setResumeFile(null);

    setCompanyName("");
    setJobTitle("");
    setJobDescription("");

    setFileInputKey((previousKey) => previousKey + 1);

    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  const atsScore = Number(
      analysisResult?.atsResult?.atsScore || 0
  );

  const scoreLabel =
      atsScore >= 80
          ? "Excellent Match"
          : atsScore >= 60
              ? "Good Match"
              : atsScore >= 40
                  ? "Needs Improvement"
                  : "Low Match";

  const scoreColor =
      atsScore >= 80
          ? "#16a34a"
          : atsScore >= 60
              ? "#2563eb"
              : atsScore >= 40
                  ? "#f59e0b"
                  : "#ef4444";

  return (
      <div className="app">

        {/* Loading Overlay */}
        {loading && (
            <div className="loading-overlay">

              <div className="loading-box">

                <div className="loading-spinner"></div>

                <h2>
                  Analyzing Your Resume
                </h2>

                <p>
                  Please wait while we analyze your resume
                  against the job description.
                </p>

                <div className="loading-steps">

                  <div className="loading-step">
                    <span>✓</span>
                    Uploading and processing resume
                  </div>

                  <div className="loading-step">
                    <span>✓</span>
                    Matching skills with job requirements
                  </div>

                  <div className="loading-step">
                    <span>✓</span>
                    Calculating ATS score
                  </div>

                  <div className="loading-step">
                    <span>✓</span>
                    Generating recommendations
                  </div>

                </div>

                <p className="loading-note">
                  This may take a few seconds...
                </p>

              </div>

            </div>
        )}

        {/* Header */}
        <header className="header">

          <div className="logo">
            AI Resume Analyzer
          </div>

          <div className="header-subtitle">
            Resume & Job Match Intelligence
          </div>

        </header>

        <main className="container">

          {/* Hero */}
          <section className="hero">

            <h1>
              Analyze Your Resume
            </h1>

            <p>
              Upload your resume and job description to get your
              ATS score, matched skills, skill gaps and recommendations.
            </p>

          </section>

          {/* Main Form */}
          <form onSubmit={handleAnalyze}>

            {/* Candidate Details */}
            <section className="card">

              <h2>
                Candidate Details
              </h2>

              <div className="form-grid">

                <div className="form-group">

                  <label>
                    Candidate Name
                  </label>

                  <input
                      type="text"
                      placeholder="Enter your name"
                      value={candidateName}
                      onChange={(e) =>
                          setCandidateName(e.target.value)
                      }
                  />

                </div>

                <div className="form-group">

                  <label>
                    Email
                  </label>

                  <input
                      type="email"
                      placeholder="Enter your email"
                      value={email}
                      onChange={(e) =>
                          setEmail(e.target.value)
                      }
                  />

                </div>

                <div className="form-group">

                  <label>
                    Phone
                  </label>

                  <input
                      type="text"
                      placeholder="Enter your phone number"
                      value={phone}
                      onChange={(e) =>
                          setPhone(e.target.value)
                      }
                  />

                </div>

                <div className="form-group">

                  <label>
                    Resume PDF
                  </label>

                  <input
                      key={fileInputKey}
                      type="file"
                      accept=".pdf"
                      onChange={(e) =>
                          setResumeFile(e.target.files[0])
                      }
                  />

                </div>

              </div>

            </section>

            {/* Job Details */}
            <section className="card">

              <h2>
                Job Details
              </h2>

              <div className="form-grid">

                <div className="form-group">

                  <label>
                    Company Name
                  </label>

                  <input
                      type="text"
                      placeholder="Example: Google"
                      value={companyName}
                      onChange={(e) =>
                          setCompanyName(e.target.value)
                      }
                  />

                </div>

                <div className="form-group">

                  <label>
                    Job Title
                  </label>

                  <input
                      type="text"
                      placeholder="Example: Software Engineer"
                      value={jobTitle}
                      onChange={(e) =>
                          setJobTitle(e.target.value)
                      }
                  />

                </div>

              </div>

              <div className="form-group">

                <label>
                  Job Description
                </label>

                <textarea
                    rows="8"
                    placeholder="Paste the job description here..."
                    value={jobDescription}
                    onChange={(e) =>
                        setJobDescription(e.target.value)
                    }
                />

              </div>

            </section>

            {/* Analyze Button */}
            <div className="analyze-container">

              <button
                  type="submit"
                  className="analyze-button"
                  disabled={loading}
              >
                {loading
                    ? "Analyzing..."
                    : "Analyze Resume"}
              </button>

            </div>

          </form>

          {/* Error */}
          {error && (
              <div className="error-box">
                {error}
              </div>
          )}

          {/* Results */}
          {analysisResult && (

              <section className="results">

                {/* Results Header */}
                <div className="results-header">

                  <h2>
                    Resume Analysis Results
                  </h2>

                  <p>
                    Resume ID: {analysisResult.resumeId}
                    {" | "}
                    Job ID: {analysisResult.jobId}
                  </p>

                </div>

                {/* ATS Score */}
                <div className="score-card">

                  <h3>
                    ATS Score
                  </h3>

                  <div
                      className="score-circle"
                      style={{
                        background: `conic-gradient(
                    ${scoreColor} ${atsScore}%,
                    #e2e8f0 ${atsScore}% 100%
                  )`,
                      }}
                  >

                    <div className="score-circle-inner">

                      <div
                          className="score"
                          style={{
                            color: scoreColor,
                          }}
                      >
                        {atsScore}%
                      </div>

                    </div>

                  </div>

                  <p
                      className="score-label"
                      style={{
                        color: scoreColor,
                      }}
                  >
                    {scoreLabel}
                  </p>

                </div>

                {/* Matched Skills */}
                <div className="result-card skill-card">

                  <div className="result-card-header">

                    <div className="result-icon matched-icon">
                      ✓
                    </div>

                    <div>

                      <h3>
                        Matched Skills
                      </h3>

                      <p>
                        Skills already present in your resume
                      </p>

                    </div>

                  </div>

                  <div className="skill-list">

                    {analysisResult.skillGap.matchedSkills?.length > 0 ? (

                        analysisResult.skillGap.matchedSkills.map(
                            (skill, index) => (

                                <span
                                    className="skill matched"
                                    key={index}
                                >
                        ✓ {skill}
                      </span>

                            )
                        )

                    ) : (

                        <p className="empty-message">
                          No matching skills found.
                        </p>

                    )}

                  </div>

                </div>

                {/* Missing Skills */}
                <div className="result-card skill-card">

                  <div className="result-card-header">

                    <div className="result-icon missing-icon">
                      !
                    </div>

                    <div>

                      <h3>
                        Skill Gaps
                      </h3>

                      <p>
                        Skills you should consider improving
                      </p>

                    </div>

                  </div>

                  <div className="skill-list">

                    {analysisResult.skillGap.missingSkills?.length > 0 ? (

                        analysisResult.skillGap.missingSkills.map(
                            (skill, index) => (

                                <span
                                    className="skill missing"
                                    key={index}
                                >
                        ! {skill}
                      </span>

                            )
                        )

                    ) : (

                        <p className="empty-message">
                          No skill gaps detected.
                        </p>

                    )}

                  </div>

                </div>

                {/* Recommendations */}
                <div className="result-card recommendations-card">

                  <div className="result-card-header">

                    <div className="result-icon recommendation-icon">
                      💡
                    </div>

                    <div>

                      <h3>
                        Recommendations
                      </h3>

                      <p>
                        Steps to improve your resume-job match
                      </p>

                    </div>

                  </div>

                  <div className="recommendations-list">

                    {analysisResult.recommendations?.length > 0 ? (

                        analysisResult.recommendations.map(
                            (recommendation, index) => (

                                <div
                                    className="recommendation-item"
                                    key={index}
                                >

                                  <div className="recommendation-number">
                                    {index + 1}
                                  </div>

                                  <div className="recommendation-text">
                                    {recommendation}
                                  </div>

                                </div>

                            )
                        )

                    ) : (

                        <p className="empty-message">
                          No recommendations available.
                        </p>

                    )}

                  </div>

                </div>

                {/* New Analysis */}
                <div className="new-analysis-container">

                  <button
                      type="button"
                      className="new-analysis-button"
                      onClick={handleNewAnalysis}
                  >
                    ↻ Analyze Another Resume
                  </button>

                </div>

              </section>

          )}

        </main>

      </div>
  );
}

export default App;