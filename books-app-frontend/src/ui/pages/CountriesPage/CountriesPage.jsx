import "./CountriesPage.css"
import {Button, CircularProgress} from "@mui/material";
import {useCountries} from "../../../hooks/useCountries.js";
import {Countries} from "../../components/countries/Countries/Countries.jsx";
import {useContext, useState} from "react";
import {UserContext} from "../../../context/UserContext.jsx";
import {AddCountryDialog} from "../../components/countries/CountryDialog/AddCountryDialog.jsx";

export const CountriesPage = () => {
    const {user} = useContext(UserContext);

    const {countries, loading, onAdd, onEdit, onDelete} = useCountries();
    const [countryDialog, setCountryDialog] = useState(false);

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
                            <h2 className="text-secondary fw-bold">Country Directory</h2>
                            {user && user.role === "ROLE_LIBRARIAN" && (
                                <Button
                                    variant="outlined"
                                    color="primary"
                                    size="small"
                                    onClick={() => {setCountryDialog(true)}}
                                >
                                    + Add Country
                                </Button>
                            )}

                        </div>
                        <Countries countries={countries} onEdit={onEdit} onDelete={onDelete} />

                        <AddCountryDialog
                            open={countryDialog}
                            onClose={() => {setCountryDialog(false)}}
                            onAdd={onAdd}
                        />
                    </>
                )}
            </div>
        </>
    )
}