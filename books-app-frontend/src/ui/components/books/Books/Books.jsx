import "./Books.css"
import {BookCard} from "../BookCard/BookCard.jsx";

export const Books = (props) => {
    const {books,authors, onEdit, onDelete} = props

    return (
        <>
            <div className="row">
                {books.map((book, index) => (
                    <BookCard key={book.id} book={book} authors={authors} onEdit={onEdit} onDelete={onDelete} />
                ))}
            </div>
        </>
    )
}