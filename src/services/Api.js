import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080", // Base URL of your backend API
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;
