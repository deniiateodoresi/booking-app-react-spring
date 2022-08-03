import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControlLabel,
    FormLabel,
    Grid,
    Radio,
    RadioGroup,
    TextField
} from "@mui/material";
import {AccommodationUnitDTO} from "../../types/Accommodation";
import axios from "axios";

export function AddAccommodationDialog(props: { town: string, openDialog: boolean, setOpenDialog: (openDialog: boolean) => void, getAllAccommodations: () => void}) {

    let chosenDescription: string = "";
    let chosenPrice: number = 0;
    const [chosenType, setChosenType] = useState("");
    const [chosenName, setChosenName] = useState("");
    let chosenTown = props.town;

    const handleChangedDescription = (event: { target: { value: string; }; }) => {
        chosenDescription = event.target.value;
    }

    const handleChangedPrice = (event: { target: { value: string; }; }) => {
        chosenPrice = event.target.value as unknown as number;
    }

    const handleChangedTown = (event: { target: { value: string; }; }) => {
        chosenTown = event.target.value;
    }

    const handleChangedType = (event: { target: { value: React.SetStateAction<string>; }; }) => {
       setChosenType(event.target.value);
    }

    const handleClose = () => {
        props.setOpenDialog(false);
    }

    const addAccommodationHelper = (accommodation: AccommodationUnitDTO) => {

        axios.post('http://localhost:9090/accommodations', {
                name: accommodation.name,
                description: accommodation.description,
                price: accommodation.price,
                type: accommodation.type,
                town: accommodation.town,
                review: accommodation.review,
                remarks: accommodation.remarks
            }).then(props.getAllAccommodations);
    }

    const handleAddAccommodation = () => {
        const newAccommodation: AccommodationUnitDTO = {
            name: chosenName,
            description: chosenDescription,
            price: +chosenPrice,
            town: chosenTown,
            remarks: [],
            review: 0,
            type: chosenType.toUpperCase()
        }
        addAccommodationHelper(newAccommodation);
        handleClose();
    }

    return (
        <Dialog open={props.openDialog} onClose={handleClose} >
            <DialogTitle>Add a new accommodation</DialogTitle>
            <DialogContent>
                <Grid container spacing={2} sx={{direction: "column", alignItems: "center" }}>
                    <Grid item xs={6}>
                        <TextField id="new-town" autoFocus label="Accommodation town" type="text" variant="standard" value={props.town} onChange={handleChangedTown}/>
                    </Grid>
                    <Grid item xs={6}>
                        <TextField id="new-name" autoFocus label="Accommodation name" type="text" variant="standard" onChange={(e) => setChosenName(e.target.value)}/>
                    </Grid>
                    <Grid item xs={6}>
                        <FormLabel id="radio-group-type">Accommodation type</FormLabel>
                        <RadioGroup aria-labelledby="radio-group-type" value={chosenType} onChange={handleChangedType}>
                            <FormControlLabel value="hotel" control={<Radio />} label="hotel" />
                            <FormControlLabel value="pension" control={<Radio />} label="pension" />
                        </RadioGroup>
                    </Grid>
                    <Grid item xs={6}>
                        <TextField id="new-price" autoFocus label="Accommodation price" type="text" variant="standard" onChange={handleChangedPrice} />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField id="new-description" autoFocus label="Accommodation description" type="text" variant="standard" sx={{width: "100%"}} onChange={handleChangedDescription} />
                    </Grid>
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button variant="contained" onClick={handleAddAccommodation}>Add accommodation</Button>
            </DialogActions>
        </Dialog>
    );
}