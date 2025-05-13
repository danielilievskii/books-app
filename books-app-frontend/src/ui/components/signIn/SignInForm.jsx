import {useContext, useState} from "react";
import {UserContext} from "../../../context/UserContext.jsx";
import {useNavigate} from "react-router";
import {handleSignIn} from "../../../actions/authActions.js";

export const SignInForm = () => {
    const { setUser } = useContext(UserContext);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const onSubmit = (e) => {
        e.preventDefault();

        const signInData = {username, password};
        handleSignIn(signInData, setUser, navigate, setError);
    };

    return (
        <form onSubmit={onSubmit}>
            <div className="mb-3 input-group">
                <span className="input-group-text"><i className="fa-solid fa-envelope"></i></span>
                <input
                    type="text"
                    id="email"
                    name="username"
                    className="form-control"
                    placeholder="Email"
                    required
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
            </div>

            <div className="mb-3 input-group">
                <span className="input-group-text"><i className="fa-solid fa-lock"></i></span>
                <input
                    type="password"
                    id="password"
                    name="password"
                    className="form-control"
                    placeholder="Password"
                    required
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>

            {error && <div className="alert alert-danger" role="alert">{error}</div>}

            <button type="submit" className="btn btn-primary w-100">Sign in</button>

            <p className="mt-3 text-center">
                Don't have an account? <a className="fw-bold text-dark" href="#">Create here</a>
            </p>
        </form>
    );
};