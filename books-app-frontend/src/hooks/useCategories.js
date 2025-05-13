import {useMemo} from "react";

export const useCategories = () => {
    const categories = useMemo(() => [
        "NOVEL",
        "THRILLER",
        "HISTORY",
        "FANTASY",
        "BIOGRAPHY",
        "CLASSICS",
        "DRAMA"
    ], []);

    return categories;
};