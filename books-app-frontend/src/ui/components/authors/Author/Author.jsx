import {Button} from "@mui/material";
import {UserContext} from "../../../../context/UserContext.jsx";
import {useContext, useState} from "react";
import {EditAuthorDialog} from "../AuthorDialog/EditAuthorDialog.jsx";
import DeleteAuthorDialog from "../AuthorDialog/DeleteAuthorDialog.jsx";

export const Author = (props) => {
    const {user} = useContext(UserContext);
    const {author, countries, onEdit, onDelete} = props;

    const [editAuthorDialog, setEditAuthorDialog] = useState(false);
    const [deleteAuthorDialog, setDeleteAuthorDialog] = useState(false);

    return (
        <tr key={author.id}>
            <td>{author.name}</td>
            <td>{author.surname}</td>
            <td>{author.country.name}</td>
            {user && user.role === "ROLE_LIBRARIAN" && (
                <td className="text-end">
                    <Button
                        variant="outlined"
                        color="primary"
                        size="small"
                        style={{marginRight: '8px'}}
                        onClick={() => {setEditAuthorDialog(true);}}
                    >
                        Edit
                    </Button>
                    <Button
                        variant="outlined"
                        color="error"
                        size="small"
                        onClick={() => {setDeleteAuthorDialog(true);}}
                    >
                        Delete
                    </Button>
                </td>
            )}

            <EditAuthorDialog
                open={editAuthorDialog}
                onClose={() => setEditAuthorDialog(false)}
                author={author}
                countries={countries}
                onEdit={onEdit}
            />
            <DeleteAuthorDialog
                open={deleteAuthorDialog}
                onClose={() => setDeleteAuthorDialog(false)}
                author={author}
                onDelete={onDelete}
            />

        </tr>
    )
}