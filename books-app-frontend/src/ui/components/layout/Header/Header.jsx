import "./Header.css"

import {Link} from "react-router-dom"
import {useContext} from "react";
import {UserContext} from "../../../../context/UserContext.jsx";
import {Button} from "@mui/material";
import {useNavigate} from "react-router";
import {handleSignOut} from "../../../../actions/authActions.js";

export const Header = () => {
    const {user, setUser} = useContext(UserContext);
    const navigate = useNavigate();

    const onSignOut = () => {
        handleSignOut(setUser, navigate);
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">
                {/* Brand */}
                <Link className="navbar-brand" to="/">BooksApp</Link>

                {/* User info right next to hamburger on mobile */}
                <div className="d-flex align-items-center d-lg-none gap-2">
                    {user ? (
                        <>
                            <img
                                src="/assets/defualt-profile-pic.png"
                                alt="Profile"
                                className="rounded-circle"
                                width="32"
                                height="32"
                            />
                            <span className="text-white small">{user.name}</span>
                            <Button
                                variant="outlined"
                                color="primary"
                                size="small"
                                onClick={onSignOut}
                            >
                                Sign out
                            </Button>
                        </>
                    ) : (
                        <Button
                            variant="outlined"
                            color="primary"
                            size="small"
                            onClick={() => navigate("/signin")}
                        >
                            Sign in
                        </Button>
                    )}
                </div>

                {/* Hamburger */}
                <button className="navbar-toggler ms-2" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                {/* Navigation Links */}
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link to="/books" className="nav-link">Books</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/authors" className="nav-link">Authors</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/countries" className="nav-link">Countries</Link>
                        </li>
                    </ul>

                    {/* Right-aligned user info on desktop */}
                    <div className="d-none d-lg-flex align-items-center gap-2">
                        {user ? (
                            <>
                                <img
                                    src="/assets/defualt-profile-pic.png"
                                    alt="Profile"
                                    className="rounded-circle"
                                    width="40"
                                    height="40"
                                />
                                <span className="text-white">{user.name}</span>
                                <Button
                                    variant="outlined"
                                    color="primary"
                                    size="small"
                                    onClick={onSignOut}
                                >
                                    Sign out
                                </Button>
                            </>
                        ) : (
                            <Button
                                variant="outlined"
                                color="primary"
                                size="small"
                                onClick={() => navigate("/signin")}
                            >
                                Sign in
                            </Button>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    )
}