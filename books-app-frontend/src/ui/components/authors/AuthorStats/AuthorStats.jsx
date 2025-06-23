import { useEffect, useState } from "react";
import { authorRepository } from "../../../../repository/authorRepository.js";
import { BarChart } from '@mui/x-charts/BarChart';
import { Paper, Typography } from "@mui/material";

export const AuthorStats = ({ countries }) => {
    const [authorsStats, setAuthorsStats] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await authorRepository.getAuthorsPerCountry();
                setAuthorsStats(response.data);
            } catch (err) {
                console.error("Failed to fetch author stats:", err);
            }
        };

        fetchData();
    }, []);

    const countryIdToName = (id) => {
        const country = countries.find(c => c.id === id);
        return country ? country.name : `ID ${id}`;
    };

    const xAxis = [
        {
            scaleType: 'band',
            data: authorsStats.map((s) => countryIdToName(s.countryId)),

        },
    ];

    const series = [
        {
            label: 'Authors',
            data: authorsStats.map((s) => s.numAuthors),
        },
    ];

    return (
        <div>
            <Paper elevation={3} sx={{ p: 2, mt: 4 }}>
                <Typography variant="h6" gutterBottom>
                    Number of Authors per Country
                </Typography>

                <BarChart
                    xAxis={xAxis}
                    yAxis={[
                        {
                            min: 0,
                            max: Math.max(...authorsStats.map(s => s.numAuthors), 1) + 1,
                            tickMinStep: 1,
                        },
                    ]}
                    series={series}
                    width={600}
                    height={400}
                />
            </Paper>
        </div>
    );
};
