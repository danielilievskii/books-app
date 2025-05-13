import {userRepository} from "../repository/userRepository.js";

export const handleSignIn = async (signInData, setUser, navigate, setError) => {
    try {
        userRepository.signIn(signInData)
            .then((response) => {
                console.log("Login successful:", response.data);
                localStorage.setItem("jwt", response.data.token);

                userRepository.me()
                    .then((response) => {
                        console.log(response.data);
                        setUser(response.data);
                    })
                    .catch((error) => {
                        console.log(error);
                    })

            })
            .catch((error) => {
                console.log(error);
            })

        navigate("/")
    } catch (err) {
        console.error("Login failed:", err);
        setError("Invalid credentials. Please try again.");
    }
};

export const handleSignOut = async (setUser, navigate) => {
    localStorage.removeItem("jwt");
    setUser(null);
    navigate("/books")
};