import {Button} from "@mui/material";
import {useContext, useState} from "react";
import {UserContext} from "../../../../context/UserContext.jsx";
import {EditBookDialog} from "../../books/BookDialog/EditBookDialog.jsx";
import DeleteBookDialog from "../../books/BookDialog/DeleteBookDialog.jsx";
import {EditCountryDialog} from "../CountryDialog/EditCountryDialog.jsx";
import DeleteCountryDialog from "../CountryDialog/DeleteCountryDialog.jsx";

export const Country = (props) => {
    const {country, onEdit, onDelete} = props;
    const {user} = useContext(UserContext);

    const [editCountryDialog, setEditCountryDialog] = useState(false);
    const [deleteCountryDialog, setDeleteCountryDialog] = useState(false);

    return (
        <tr key={country.id}>
            <td>{country.name}</td>
            <td>{country.continent}</td>
            {user && user.role === "ROLE_LIBRARIAN" && (
                <td className="text-end">
                    <Button
                        variant="outlined"
                        color="primary"
                        size="small"
                        style={{marginRight: '8px'}}
                        onClick={() => {setEditCountryDialog(true)}}
                    >
                        Edit
                    </Button>
                    <Button
                        variant="outlined"
                        color="error"
                        size="small"
                        onClick={() => {setDeleteCountryDialog(true)}}
                    >
                        Delete
                    </Button>
                </td>
            )}

            <EditCountryDialog
                open={editCountryDialog}
                onClose={() => setEditCountryDialog(false)}
                country={country}
                onEdit={onEdit}
            />
            <DeleteCountryDialog
                open={deleteCountryDialog}
                onClose={() => setDeleteCountryDialog(false)}
                country={country}
                onDelete={onDelete}
            />

        </tr>
    )
}