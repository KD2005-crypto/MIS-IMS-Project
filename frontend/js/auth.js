const BASE_URL = "https://mis-ims-project-1.onrender.com";

/* ================= LOGIN ================= */
async function loginUser(event) {
  event.preventDefault();

  const email = document.getElementById("loginEmail").value;
  const password = document.getElementById("loginPassword").value;

  try {
    const response = await fetch(`${BASE_URL}/api/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ email, password })
    });

    const text = await response.text();

    if (response.ok) {
      alert("Login successful");
      window.location.href = "dashboard.html";
    } else {
      alert(text);
    }
  } catch (error) {
    alert("Server error. Try again later.");
    console.error(error);
  }
}

/* ================= REGISTER ================= */
async function registerUser(event) {
  event.preventDefault();

  const name = document.getElementById("registerName").value;
  const email = document.getElementById("registerEmail").value;
  const password = document.getElementById("registerPassword").value;

  try {
    const response = await fetch(`${BASE_URL}/api/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ name, email, password })
    });

    const text = await response.text();

    if (response.ok) {
      alert("Registration successful");
      window.location.href = "index.html";
    } else {
      alert(text);
    }
  } catch (error) {
    alert("Server error. Try again later.");
    console.error(error);
  }
}
