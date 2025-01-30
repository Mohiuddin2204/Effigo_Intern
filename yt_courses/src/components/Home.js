import React,{useEffect} from "react";
import {Container,Button} from 'reactstrap';
const Home=()=>{

useEffect(()=>{
        document.title="Home || Stretford END";
    },[]);
    
    return(
        <div style={{ background: 'lightblue' }}>
            <Container className="text-center" >
                <h2>Welcome to my website</h2>
                <p>STRETFROD END</p>
                <Container>
                    <Button color="primary" outline>button</Button>
                </Container>
        </Container>
        </div>
    );
}

export default Home;