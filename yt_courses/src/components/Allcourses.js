/* eslint-disable eqeqeq */
/* eslint-disable no-unused-vars */
import React,{useState,useEffect} from 'react';
import Course from './Course';
import base_url from '../api/bootapi';
import axios from 'axios';
import { toast } from 'react-toastify';

const Allcourses=()=>{

    useEffect(()=>{
        document.title="All courses";
    },[]);

//call server
const getAllCoursesFromServer=()=>{
    axios.get(`${base_url}/courses`).then(
        (response)=>{
            console.log(response);
            toast.success("courses loaded"
            );
            setCourses(response.data);
        },
        (error)=>{
            console.log(error);
            toast.error("wrongg");
        }
    )
}

//loading course func
useEffect(()=>{
    getAllCoursesFromServer();
},[]);

const updateCourses=(id)=>{
    setCourses(courses.filter((c)=>c.id != id));
}

    const[courses,setCourses]=useState([
        
    ]);

    return(
<div>
    <h1>all courses</h1>
        <p>List of courses:</p>
        {
            courses.length>0 ?
            courses.map((item)=>{
               return <Course course={item} 
               update={updateCourses}/>
            }):"No courses"
        }
</div>
    );
}

export default Allcourses;