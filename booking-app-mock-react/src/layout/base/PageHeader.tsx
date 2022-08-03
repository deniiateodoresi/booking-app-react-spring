import React from 'react';
import {Grid, Typography} from "@mui/material";

export function PageHeader() {
    return (
    <Grid container item xs={12} sx={{ height: 100, backgroundColor: '#5D6C9B'}} alignItems="center" justifyContent="center">
        <Typography variant='h4' sx={{fontWeight: "bold"}}>Travel all around the world</Typography>
    </Grid>
    );
}