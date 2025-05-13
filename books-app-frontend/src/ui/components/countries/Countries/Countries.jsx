import "./Countries.css"
import {Country} from "../Country/Country.jsx";
import {useContext} from "react";
import {UserContext} from "../../../../context/UserContext.jsx";

export const Countries = (props) => {
    const {user} = useContext(UserContext);
    const {countries, onEdit, onDelete} = props

    return (
        <>
            <div className="row">
                <div className="col-12 mx-auto">
                    <div className="table-responsive shadow-sm rounded-4 overflow-hidden">
                        <table className="table table-striped align-middle mb-0">
                            <thead className="table-light">
                            <tr>
                                <th>Name</th>
                                <th>Continent</th>
                                {user && user.role === "ROLE_LIBRARIAN" && (
                                    <th className="text-end">Actions</th>
                                )}

                            </tr>
                            </thead>
                            <tbody>
                            {countries.map((country, index) => (
                                <Country key={country.id} country={country} onEdit={onEdit} onDelete={onDelete} />
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );


}