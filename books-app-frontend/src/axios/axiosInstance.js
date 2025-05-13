import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api",
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