import React, {useState} from 'react';
import {AccommodationUnitDTO} from "../../types/Accommodation";
import {Button, Card, CardContent, Tooltip, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";
import {modifiedAccommodation} from "../../helpers/accommodationSlice";
import {useDispatch} from "react-redux";
import axios from "axios";


export function AccommodationCard(props: { accommodation: AccommodationUnitDTO, getAllAccommodations: () => void }) {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const toDetailsPage = (accommodation: AccommodationUnitDTO) => {
        // before: passing data with useNavigate and state option
        // navigate("/DetailsPageLayout",
        //   { state:  accommodation }
        // )
        dispatch(modifiedAccommodation(props.accommodation));
        navigate("/DetailsPageLayout")
    };

    const handleDeleteAccommodation = () => {
        axios.delete('http://localhost:9090/accommodations', {params: {name: props.accommodation.name}})
            .then(props.getAllAccommodations);
    }

    const [bgColor, setBgColor] = useState("#B2D2E7");

    return (
        <Card sx={{m: 2, backgroundColor: "#B2D2E7", textAlign: "center"}}>
            <CardContent sx={{alignItems: "center"}}>
                <Tooltip title="Go to the details page" >
                    {/*before: passing data with useNavigate and state option*/}
                    {/*<Typography variant="h5" component="h2" sx={{ cursor: "pointer"}}  onClick={() => {toDetailsPage(props.accommodation)}}>{props.accommodation.name}*/}
                    {/*</Typography>*/}
                    <Typography variant="h5" component="h2" sx={{cursor: "pointer", backgroundColor: bgColor, borderRadius: "25px"}} onClick={() => { toDetailsPage(props.accommodation) } } onMouseEnter={() => setBgColor("#A0BDD0")} onMouseLeave={() => setBgColor("#B2D2E7")}>
                        {props.accommodation.name}
                    </Typography>
                </Tooltip>
                <Typography variant="h5" component="h2">
                    {props.accommodation.town} &#9830; {props.accommodation.type}
                </Typography>
                <Typography>Rating: {props.accommodation.review}</Typography>
                <Typography>Price per night/person: {props.accommodation.price}</Typography>
                <Button variant="contained" sx={{mx: "auto"}} onClick={handleDeleteAccommodation}>Delete</Button>
            </CardContent>

        </Card>

    );
}
