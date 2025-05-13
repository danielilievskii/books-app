import axiosInstance from "../axios/axiosInstance.js";

export const userRepository = {
    me: async () => {
        return await axiosInstance.get("/auth/me")
    },

    signIn: async (signInData) => {
        return await axiosInstance.post("/auth/signin", signInData)
    }
}