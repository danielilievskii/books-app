import "./Authors.css"
import {Author} from "../Author/Author.jsx";
import {useContext} from "react";
import {UserContext} from "../../../../context/UserContext.jsx";
import {useCountries} from "../../../../hooks/useCountries.js";

export const Authors = (props) => {
    const {authors, countries, onEdit, onDelete} = props
    const {user} = useContext(UserContext);

    return (
        <>
            <div className="row">
                <div className="col-12 mx-auto">
                    <div className="table-responsive shadow-sm rounded-4 overflow-hidden">
                        <table className="table table-striped align-middle mb-0">
                            <thead className="table-light">
                            <tr>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Country</th>
                                {user && user.role === "ROLE_LIBRARIAN" && (
                                    <th className="text-end">Actions</th>
                                )}
                            </tr>
                            </thead>
                            <tbody>
                            {authors.map((author, index) => (
                                <Author key={author.id} author={author} countries={countries} onEdit={onEdit} onDelete={onDelete} />
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );


}