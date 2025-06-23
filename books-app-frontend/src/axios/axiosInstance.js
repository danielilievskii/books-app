import axios from "axios";

const VITE_BACKEND_HOST = import.meta.env.VITE_BACKEND_HOST || "localhost";
const VITE_BACKEND_PORT = import.meta.env.VITE_BACKEND_PORT || "8080";
const VITE_BACKEND_BASE_PATH = import.meta.env.VITE_BACKEND_BASE_PATH || '/api';

const axiosInstance = axios.create({
    baseURL: `http://${VITE_BACKEND_HOST}:${VITE_BACKEND_PORT}${VITE_BACKEND_BASE_PATH}`,
    headers: {
        "Content-Type": "application/json",
    }

})

axiosInstance.interceptors.request.use(
    config => {
        const token = localStorage.getItem("jwt");
        if (token) {
            console.log("Using token:" + token);
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => Promise.reject(error)
);

export default axiosInstance;