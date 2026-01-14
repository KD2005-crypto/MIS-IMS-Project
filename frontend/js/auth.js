// ===============================
// BACKEND BASE URL (LIVE)
// ===============================
const API_BASE_URL = "https://mis-ims-project.onrender.com";

// ===============================
// LOGIN
// ===============================
async function loginUser(event) {
    event.preventDefault();

    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;

    try {
        const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include", // VERY IMPORTANT for session
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        if (!response.ok) {
            alert("Invalid email or password");
            return;
        }

        const data = await response.json();

        // Save session info (optional but useful)
        localStorage.setItem("userRole", data.role);
        localStorage.setItem("userEmail", data.email);

        // Redirect to dashboard
        window.location.hre
