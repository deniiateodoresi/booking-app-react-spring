import React from 'react';
import {Card, CardContent, Typography} from "@mui/material";

export function ReviewCard (props: {review: string}) {
    return (
        <Card sx={{m: 2, p: 4, fontSize: "10", backgroundColor: '#c8d5ff', border: '1px solid #888888', alignItems: "center", width: 300, boxShadow: "5px 10px 8px #888888"}}>
            <CardContent>
                <Typography variant="h5" component="h2">{props.review} </Typography>
            </CardContent>
        </Card>

    );
}