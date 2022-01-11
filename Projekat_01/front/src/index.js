import React from "react";
import ReactDOM from "react-dom";
import {
  Route,
  Link,
  HashRouter as Router,
  Routes
} from "react-router-dom";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import { Container, Navbar, Nav, Button } from "react-bootstrap";
import Login from "./components/Login/Login";
import { logout } from "./services/auth";
import Proizvod from "./components/proizvod/Proizvod.js";
import Kupovina from "./components/kupovina/Kupovina.js";

class App extends React.Component {
  render() {
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand>
              <Navbar.Brand as={Link} to="/">
                HOME
              </Navbar.Brand>
              {/*className="mr-auto" podesi ovu grupu Nav Link-ova da se "rasire" sto je vise moguce,
              i zbog toga je dugme Log in/Log out skroz sa leve strane*/}
              {window.localStorage['role']=="ROLE_ADMIN"?
              <Nav>
                <Nav.Link as={Link} to="/proizvod">
                  PROIZVODI
                </Nav.Link>
              </Nav>:null}
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/kupovina">
                  KUPOVINA
                </Nav.Link>
              </Nav>
              

              {window.localStorage['jwt'] ? 
                  <Button onClick = {()=>logout()}>Log out</Button> :
                  <Nav.Link as={Link} to="/login">Log in</Nav.Link>
              }
            </Navbar>

            <Container style={{marginTop:25}}>
              <Routes>
                <Route path="/" element={<Home/>} />                
                <Route path="/proizvod" element={<Proizvod/>} />
                <Route path="/kupovina" element={<Kupovina/>} />
                <Route path="/login" element={<Login/>}/>
                <Route path="*" element={<NotFound/>} />
              </Routes>
            </Container>
          </Router>
        </div>
      );
  }
}

ReactDOM.render(<App />, document.querySelector("#root"));
