const BASE_URL = "http://localhost:8080/api/auth";

// REGISTER
async function registerUser() {
    const data = {
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        role: "USER"
    };

    const response = await fetch(`${BASE_URL}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    const result = await response.text();
    alert(result);

    if (response.ok) {
        window.location.href = "login.html";
    }
}

// LOGIN
async function loginUser(event) {
    event.preventDefault();

    const data = {
        email: document.getElementById("loginEmail").value,
        password: document.getElementById("loginPassword").value
    };

    const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        // SESSION START
        localStorage.setItem("isLoggedIn", "true");

        alert("Login successful");
        window.location.href = "dashboard.html";
    } else {
        alert("Invalid email or password");
    }
}
