import "./BooksPage.css"
import {useBooks} from "../../../hooks/useBooks.js";
import {Box, Button, CircularProgress} from "@mui/material";
import {Books} from "../../components/books/Books/Books.jsx";
import {Authors} from "../../components/authors/Authors/Authors.jsx";
import {useContext, useState} from "react";
import {UserContext} from "../../../context/UserContext.jsx";
import {AddBookDialog} from "../../components/books/BookDialog/AddBookDialog.jsx";
import {useAuthors} from "../../../hooks/useAuthors.js";

export const BooksPage = () => {

    const {user} = useContext(UserContext);
    const {authors} = useAuthors();
    const {books, loading, onAdd, onEdit, onDelete} = useBooks();
    const [bookDialog, setBookDialog] = useState(false);

    return (
        <div className="container mt-5">
            {loading && (
                <div className="d-flex justify-content-center align-items-center h-100">
                    <CircularProgress color="secondary"/>
                </div>
            )}
            {!loading && (
                <>
                    <div className="d-flex justify-content-between align-items-center mb-4">
                        <h2 className="text-secondary fw-bold">Book Directory</h2>
                        {user && user.role === "ROLE_LIBRARIAN" && (
                            <Button
                                variant="outlined"
                                color="primary"
                                size="small"
                                onClick={() => {
                                    setBookDialog(true);
                                }}
                            >
                                + Add Book
                            </Button>
                        )}

                    </div>
                    <Books books={books} authors={authors} onEdit={onEdit} onDelete={onDelete} />

                    <AddBookDialog
                        authors={authors}
                        open={bookDialog}
                        onClose={() => setBookDialog(false)}
                        onAdd={onAdd}
                    />
                </>
            )}
        </div>
    )
}