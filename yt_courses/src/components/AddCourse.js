/* eslint-disable no-unused-vars */
import React,{useEffect,useState} from 'react';
import {Form,FormGroup,Input,Container,Button} from 'reactstrap';
import axios from 'axios';
import base_url from '../api/bootapi';
import { toast } from 'react-toastify';
const AddCourse=()=>{

useEffect(()=>{
        document.title="Home || Stretford END";
    },[]);

const [course,setCourse]=useState({ });
//form handler
const handleForm=(e)=>{
    console.log(course);
    postDatatoServer(course);
    e.preventDefault();
};

//post data on server
const postDatatoServer=(data)=>{
axios.post(`${base_url}/courses`,data).then(
    (response)=>{
        console.log(response);
        console.log("success");
        toast.success("added");
    },
    (error)=>{
        console.log(error);
        console.log("error");
        toast.error("error!!");
    }
)
}


    return(
        <div>
            <h1 className='text-center my-3'>Fill course details</h1>
            <Form onSubmit={handleForm}>
<FormGroup>
    <label for="userId">CourseId</label>
    <Input type="text" placeholder="Enter" 
    name="userId" id="userId"
    onChange={(e)=>{
        setCourse({...course,id:e.target.value})
    }}/>
</FormGroup>

<FormGroup>
    <label for="title">CourseTitle</label>
    <Input type="text" placeholder="Enter" 
    name="title" id="title"
    onChange={(e)=>{
        setCourse({...course,title:e.target.value})
    }}/>
</FormGroup>

<FormGroup>
    <label for="description">CourseDesc</label>
    <Input type="textarea" placeholder="Enter" 
    name="description" id="description"
    style={{height:155}}
    onChange={(e)=>{
        setCourse({...course,description:e.target.value})
    }}/>
</FormGroup>

<Container className='text-center'>
    <Button type="submit" color="succcess">Add</Button>
    <Button type="reset" color="warning ml-3" onClick={()=>{
        setCourse({id:"",title:"",description:""});
    }}>Clear</Button>
</Container>
            </Form>
        </div>
    );
}

export default AddCourse;