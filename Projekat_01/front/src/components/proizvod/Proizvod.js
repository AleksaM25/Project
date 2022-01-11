import React from "react";
import { withNavigation } from "../../routeconf";
import { FormGroup, FormLabel, ButtonGroup, Form, Button, Table} from "react-bootstrap";
import SprintsAxios from "../../apis/SprintsAxios";




class Proizvod extends React.Component {

    constructor(props) {
        super(props);

        const proizvod = {
            naziv: "",
            cena: "",
            stanje: "",
            kategorijaID: -1
        };

        this.state = {
            proizvod: proizvod,
            kategorija : [],
            showSearch : false,
            search : {minCena: "", maxCena: "", kategorija:""},
            pageNo: 0,
            TotalPages: 1
        };
    }

    componentDidMount(){
        this.getData();
    }

    async getData() {
        await this.getKategorija();
    }

    async getKategorija() {
        try{
            let rezultat = await SprintsAxios.get("/kategorije");
            if(rezultat && rezultat.status === 200) {
                this.setState({
                kategorija: rezultat.data,
                });
            }
        }catch(error) {
            alert("Nije uspelo dobavljanje");
        }
    }

    canCreateProizvod(){
        const proizvod = this.state.proizvod
        return proizvod.naziv!="" && 
          proizvod.kategorijaID != -1
      }

      async doAdd() {
        try {
          await SprintsAxios.post("/proizvodi/", this.state.proizvod);
    
          let proizvod = {
            naziv: "",
            cena: "",
            stanje: "",
            kategorijaID: -1,
          };
          this.setState({ proizvod: proizvod });
            } catch (error) {
          alert("Nije uspelo dodavanje.");
        }
      }

      addValueInputChange(event) {
        let control = event.target;
    
        let name = control.name;
        let value = control.value;
    
        let proizvod = this.state.proizvod;
        proizvod[name] = value;
    
        this.setState({ proizvod: proizvod });
      }
    

    render() {
        const proizvod = this.state.proizvod; 
        return (
            <div>
                <h1>Ovaj deo je za Admina</h1>
                {window.localStorage['role']=="ROLE_ADMIN"?
                <Form>
                    <Form.Group>
                        <Form.Label>Naziv</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="naziv"
                            value={this.state.proizvod.naziv}
                            as="input"
                            placeholder="Naziv Proizvoda"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Cena</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="cena"
                            value={this.state.proizvod.cena}
                            as="input"
                            type="number"
                            placeholder="Cena Proizvoda"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Stanje</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="stanje"
                            value={this.state.proizvod.stanje}
                            as="input"
                            type="number"
                            placeholder="Stanje Proizvoda"
                        ></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Kategorija</Form.Label>
                        <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="kategorijaID"
                            value={this.state.proizvod.kategorijaID}
                            as="select"
                            placeholder="Kategorija Proizvoda"
                        >
                            <option value={-1}></option>
                            {this.state.kategorija.map((kategorija) => {
                                return (
                                    <option value={kategorija.id} key={kategorija.id}>
                                        {kategorija.naziv}
                                    </option>
                                );
                            })}
                        </Form.Control>
                    </Form.Group>
                    <Button variant="primary" onClick={() => this.doAdd()}
                    >DODAJ</Button>
                </Form>:null}
            </div>
        );
    }
}

export default withNavigation(Proizvod);