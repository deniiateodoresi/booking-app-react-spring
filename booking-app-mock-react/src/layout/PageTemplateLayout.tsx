import React, {useEffect, useState} from 'react';
import {PageHeader} from "./base/PageHeader";
import {AccommodationList} from "./AccommodationList";
import {Button, FormControl, Grid, InputLabel, Typography} from '@mui/material';
import {FiltersDropdownContent} from "./base/FiltersDropdownContent";
import {useLoadAccommodationUnits} from "../hooks/useLoadAccommodationUnits";
import {AccommodationUnitDTO} from "../types/Accommodation";
import {AddAccommodationDialog} from "./base/AddAccommodationDialog";

export function PageTemplateLayout(props: {name: string}) {

    const {accommodations, getAllAccommodations} = useLoadAccommodationUnits();

    const initialSelectedTownState = localStorage.getItem("selectedTown") || "";
    const[selectedTown, setSelectedTown] = useState("");

    const[hotelsToDisplay, setHotelsToDisplay] = useState<AccommodationUnitDTO[]>([]);
    const[isFiltered, setIsFiltered] = useState(false);
    const[isStarted, setIsStarted] = useState(false);

    useEffect(()=> {
        setIsFiltered(true);
        if(initialSelectedTownState == "")
            setHotelsToDisplay(accommodations);
        else {
            setIsStarted(!isStarted);
        }
    }, [accommodations])

    useEffect(() =>{
        setIsFiltered(false);
    }, [hotelsToDisplay])

    useEffect(() =>{
        const filters: AccommodationUnitDTO[] = accommodations.filter((accommodation: AccommodationUnitDTO) => accommodation.town == initialSelectedTownState);
        setHotelsToDisplay(filters);
    }, [isStarted])

    useEffect(() =>{
        const filters: AccommodationUnitDTO[] = accommodations.filter((accommodation: AccommodationUnitDTO) => accommodation.town == selectedTown || selectedTown == "");
        setHotelsToDisplay(filters);
    }, [selectedTown])

    const childToParent = (town: string) => {
        setIsFiltered(true);
        setSelectedTown(town);
    }

    const [openDialog, setOpenDialog] = useState<boolean>(false);

    const handleAddAccommodation = () => {
        setOpenDialog(true);
    }


    return (
            <Grid container sx={{ height: '100vh' }} >
                <PageHeader />
                <Grid container justifyContent="center" sx={{backgroundColor: '#c8d5ff', height: 'calc(100vh - 100px)'}}>
                    <Grid container item xs={2} sx={{ backgroundColor: '#EFC797'}}>
                        <FormControl sx={{width: 200, m: 5}} >
                            <InputLabel id="filter-label">Filter by town</InputLabel>
                            <FiltersDropdownContent childToParent={childToParent} initialState={initialSelectedTownState}/>
                            <Button variant="contained" sx={{marginTop: 2, backgroundColor: "#E08B23"}} onClick={handleAddAccommodation}>Add accommodation</Button>
                            <AddAccommodationDialog town={selectedTown == "" ? initialSelectedTownState : selectedTown} openDialog={openDialog} setOpenDialog={setOpenDialog} getAllAccommodations={getAllAccommodations}/>
                        </FormControl>
                    </Grid>
                    <Grid container spacing={0} item xs={4} sx={{ backgroundColor: '#8da0db', overflowY: "scroll", height: 'calc(100vh - 100px)'}}  >
                        <Grid item sx={{height: "80px"}}>
                            <Typography variant="h5" sx={{ml: 5, mt: 2, mb:0,  fontWeight: "bold", height: "80px"}}>
                                Properties found on our website:
                            </Typography>
                        </Grid>
                        <Grid item sx={{ mx: "auto"}}>
                            {
                                isFiltered ? <div>Loading</div> : <AccommodationList accommodations={hotelsToDisplay} getAllAccommodations={getAllAccommodations} />
                            }
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
    );
}