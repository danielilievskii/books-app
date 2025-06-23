import "./AuthorsPage.css"
import {useAuthors} from "../../../hooks/useAuthors.js";
import {Button, CircularProgress} from "@mui/material";
import {Books} from "../../components/books/Books/Books.jsx";
import {Authors} from "../../components/authors/Authors/Authors.jsx";
import {useContext, useState} from "react";
import {UserContext} from "../../../context/UserContext.jsx";
import {AddCountryDialog} from "../../components/countries/CountryDialog/AddCountryDialog.jsx";
import {AddAuthorDialog} from "../../components/authors/AuthorDialog/AddAuthorDialog.jsx";
import {useCountries} from "../../../hooks/useCountries.js";
import {AuthorStats} from "../../components/authors/AuthorStats/AuthorStats.jsx";

export const AuthorsPage = () => {
    const {user} = useContext(UserContext);
    const {authors, loading, onAdd, onEdit, onDelete} = useAuthors();
    const {countries} = useCountries();

    const [authorDialog, setAuthorDialog] = useState(false);

    return (
        <>
            <div className="container mt-5">
                {loading && (
                    <div className="d-flex justify-content-center align-items-center h-100">
                        <CircularProgress color="secondary" />
                    </div>
                )}
                {!loading && (
                    <>
                        <div className="d-flex justify-content-between align-items-center mb-4">
                            <h2 className="text-secondary fw-bold">Author Directory</h2>
                            {user && user.role === "ROLE_LIBRARIAN" && (
                                <Button
                                    variant="outlined"
                                    color="primary"
                                    size="small"
                                    onClick={() => {setAuthorDialog(true)}}
                                >
                                    + Add Author
                                </Button>
                            )}

                        </div>
                        <Authors authors={authors} countries={countries} onEdit={onEdit} onDelete={onDelete} />

                        <AddAuthorDialog
                            countries={countries}
                            open={authorDialog}
                            onClose={() => {setAuthorDialog(false)}}
                            onAdd={onAdd}
                        />

                        <AuthorStats countries={countries} />
                    </>
                )}
            </div>
        </>
    )
}