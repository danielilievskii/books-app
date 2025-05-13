import {useParams} from "react-router-dom";
import {useContext} from "react";
import {BookDetails} from "../../components/books/BookDetails/BookDetails.jsx";
import {useBookDetails} from "../../../hooks/useBookDetails.js";
import {Button, CircularProgress} from "@mui/material";
import {Books} from "../../components/books/Books/Books.jsx";
import {UserContext} from "../../../context/UserContext.jsx";

export const BookDetailsPage = () => {

    const {id} = useParams();
    const {book, loading} = useBookDetails(id);
    const {user} = useContext(UserContext);

    return(
        <div className="container mt-5">
            {loading && (
                <div className="d-flex justify-content-center align-items-center h-100">
                    <CircularProgress color="secondary"/>
                </div>
            )}
            {!loading && book && (
                <>
                    <BookDetails book={book} user={user}  />
                </>
            )}
        </div>
    )
}