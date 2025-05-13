import "./BookCard.css"
import {useNavigate} from "react-router";
import {Button, IconButton, Tooltip} from "@mui/material";
import {useContext, useState} from "react";
import {EditBookDialog} from "../BookDialog/EditBookDialog.jsx";
import DeleteBookDialog from "../BookDialog/DeleteBookDialog.jsx";
import DeleteIcon from "@mui/icons-material/Delete";
import EditSquareIcon from '@mui/icons-material/EditSquare';
import BorderColorIcon from '@mui/icons-material/BorderColor';
import EditNoteIcon from '@mui/icons-material/EditNote';
import {UserContext} from "../../../../context/UserContext.jsx";


export const BookCard = (props) => {
    const {book, authors, onEdit, onDelete} = props;

    const {user} = useContext(UserContext);
    const navigate = useNavigate();

    const [editBookDialog, setEditBookDialog] = useState(false);
    const [deleteBookDialog, setDeleteBookDialog] = useState(false);


    return (
        <div key={book.id} className="col-md-3 mb-4 position-relative">
            <div className="card shadow-sm border-0 rounded-4 h-100">
                {/* Top-right Edit/Delete buttons */}
                {user && user.role === "ROLE_LIBRARIAN" && (
                    <div className="position-absolute top-0 end-0 p-2 d-flex">
                        <IconButton
                            onClick={() => setEditBookDialog(true)}
                            sx={{color: 'black', padding: '3px'}}
                            size="small"
                        >
                            <EditNoteIcon/>
                        </IconButton>

                        <IconButton
                            onClick={() => setDeleteBookDialog(true)}
                            sx={{color: 'black', padding: '3px'}}
                            size="small"
                        >
                            <DeleteIcon/>
                        </IconButton>
                    </div>
                )}


                <div className="card-body d-flex flex-column">
                    <h5 className="card-title fw-bold mb-2">{book.name}</h5>
                    <h6 className="card-subtitle text-muted mb-2">
                        {book.author.name} {book.author.surname}
                    </h6>
                    <span className="badge bg-primary align-self-start mb-3">
                {book.category}
            </span>

                    <div className="mt-auto">
                        <p className="card-text">
                            <strong>Available Copies:</strong> {book.availableCopies}
                        </p>
                        <div className="d-flex justify-content-center align-items-center gap-2">
                            <Button
                                onClick={() => navigate(`/books/${book.id}`)}
                                variant="outlined"
                                color="primary"
                                size="small"
                            >
                                View Details
                            </Button>
                            {user && user.role === "ROLE_LIBRARIAN" && (
                                <Button
                                    variant="outlined"
                                    color="primary"
                                    size="small"
                                >
                                    Add to Wishlist
                                </Button>
                            )}

                        </div>
                    </div>
                </div>
            </div>
            <EditBookDialog
                book={book}
                authors={authors}
                open={editBookDialog}
                onClose={() => setEditBookDialog(false)}

                onEdit={onEdit}
            />
            <DeleteBookDialog
                open={deleteBookDialog}
                onClose={() => setDeleteBookDialog(false)}
                book={book}
                onDelete={onDelete}
            />
        </div>

    );
}