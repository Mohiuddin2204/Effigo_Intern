import React from "react";
import {Card, CardBody } from "reactstrap";
function Header({name,title}){
    return(
        <div>
          <Card className="my-3 bg-warning">
            <CardBody>
            <h1 className="text-center my-5">Welcome to courses application</h1>
            </CardBody>
          </Card>
       
        </div>
    );
}
export default Header;