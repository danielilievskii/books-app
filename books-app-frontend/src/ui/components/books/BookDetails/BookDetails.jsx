import {Button} from "@mui/material";
import {useContext} from "react";
import {UserContext} from "../../../../context/UserContext.jsx";

export const BookDetails = ({ book, user }) => {
    if (!book) return <div className="text-center mt-5">No book data available.</div>;

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-8">
                    <div className="card shadow-lg border-0">
                        <div className="card-header bg-light d-flex justify-content-between align-items-center">
                            <h3 className="mb-0 text-primary">{book.name}</h3>
                            {user && (
                                <Button
                                    variant="outlined"
                                    color="primary"
                                    size="small"
                                >
                                    Add to Wishlist
                                </Button>
                            )}

                        </div>
                        <div className="card-body">
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item">
                                    <strong>Category:</strong> {book.category}
                                </li>
                                <li className="list-group-item">
                                    <strong>Available Copies:</strong> {book.availableCopies}
                                </li>
                                <li className="list-group-item">
                                    <strong>Author:</strong> {book.author.name} {book.author.surname}
                                </li>
                                <li className="list-group-item">
                                    <strong>Country:</strong> {book.author.country.name} ({book.author.country.continent})
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};