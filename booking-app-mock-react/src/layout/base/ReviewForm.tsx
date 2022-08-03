import React, {useState} from "react";
import {Button, FormControl, Rating, TextareaAutosize, Typography} from "@mui/material";
import {addReviewHelper} from "../../helpers/addReviewHelper";

export function ReviewForm (props: {name: string, handleAddedReview: (remark: string) => void}) {

    const[rating, setRating] = useState<number | null>(0);
    const[remarks, setRemarks] = useState("");

    const handleChangedRemark = (event: { target: { value: React.SetStateAction<string>; }; }) => {
        setRemarks(event.target.value);
    }

    const handleSubmitReview = () => {
        addReviewHelper(props.name, rating, remarks);
        props.handleAddedReview(remarks);
    }

    return (
        <FormControl sx={{backgroundColor: "#8da0db", width: 500, alignItems: "center", height: 300, mt: 3, mx: "auto", p: 5}}>
            <Typography variant="h5" sx={{fontWeight: "bold"}}>
                Leave a review about this property
            </Typography>
            <Typography>Rate this place:</Typography>
            <Rating name="simple-controlled" value={rating} onChange={(event, newValue) =>  { setRating(newValue) }}/>
            <TextareaAutosize placeholder="Write your review here" onChange={handleChangedRemark} style={{ marginTop: 10, width: 500, height: 200}}/>
            <Button variant="contained" sx={{marginTop: 2}} onClick={handleSubmitReview}>Post your review</Button>
        </FormControl>
    );
}