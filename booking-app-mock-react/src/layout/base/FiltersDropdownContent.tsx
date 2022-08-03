import React, {useState} from 'react';
import {MenuItem, Select, SelectChangeEvent} from "@mui/material";
import {useLoadTowns} from "../../hooks/useLoadTowns";

export function FiltersDropdownContent(props: {childToParent: (arg0: string) => void, initialState: string}) {
    const {towns} = useLoadTowns();

    const[selectedTown, setSelectedTown] = useState(props.initialState);

    const handleSelectedChange = (event: SelectChangeEvent) => {
        setSelectedTown(event.target.value as string);
        localStorage.setItem("selectedTown", event.target.value);
        props.childToParent(event.target.value as string);
    }

    return (
        <Select labelId="filter-label" sx={{backgroundColor: "#E08B23"}} value={selectedTown} onChange={handleSelectedChange}>
            {
                towns.map((town: string, index) => (
                    <MenuItem sx={{backgroundColor: "#F1BC7D"}} value={town} key={index}>{town}</MenuItem>
                ))
            }
        </Select>

    );
}