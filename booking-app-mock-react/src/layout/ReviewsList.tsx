import React from "react";
import {Grid} from "@mui/material";
import {ReviewCard} from "./base/ReviewCard";

export function ReviewsList (props: {reviews: string[]}){

    const DisplayReviews = () => {

        console.log("am primit reviews: ", props.reviews);
        if(props.reviews.length > 0){
            return (
                props.reviews.map((review: string, index) => {
                    const key = "review_" + index;
                    return <ReviewCard key={key} review={review}/>
                })
            );
        }
    }

    return (
        <Grid container alignItems="center" direction="column">
            <>{DisplayReviews()}</>
        </Grid>
    )
}