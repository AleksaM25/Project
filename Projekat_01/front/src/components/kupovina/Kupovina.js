import React from "react";
import {ButtonGroup, Form, Button, Table, Collapse} from "react-bootstrap";
import { withNavigation } from "../../routeconf";
import SprintsAxios from "../../apis/SprintsAxios";



class Kupovina extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            proizvod : [],
            kategorija: [],
            kolicine: {},
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
        await this.getProizvod(0);
    }

    async getProizvod(page) {
        let config = {params:{pageNo: page}};

        if(this.state.search.minCena != "") {
            config.params.minCena = this.state.search.minCena;
        }
        if(this.state.search.maxCena != "") {
            config.params.maxCena = this.state.search.maxCena;
        }
        if(this.state.search.kategorija != "") {
            config.params.kategorija = this.state.search.kategorija;
        }

        try{
            let rezultat = await SprintsAxios.get("/proizvodi", config);
            if(rezultat && rezultat.status === 200) {
                this.setState({
                    pageNo: page,
                    proizvod: rezultat.data,
                    totalPages: rezultat.headers["total-pages"],
                });
            }
        }catch (error) {
            alert("Nije uspelo dobavljanje");
        }
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

    kolicinaChange(proizvodId, event) {
        let value = event.target.value;
        const kolicine = this.state.kolicine;
        kolicine[proizvodId] = value;
        this.setState({
            kolicine: kolicine
        });
    }

    async kupi(proizvodId) {
        const kolicina = this.state.kolicine[proizvodId];
        const porudzbina = {
            kolicina: kolicina,
            proizvodID: proizvodId
        }

        await SprintsAxios.post("/porudzbine", porudzbina);

    }

    async doDelete(proizvodID) {
        try {
          await SprintsAxios.delete("/proizvodi/" + proizvodID);
          var nextPage
          if(this.state.pageNo==this.state.TotalPages-1 && this.state.proizvod.length==1){
            nextPage = this.state.pageNo-1
          }else{
            nextPage = this.state.pageNo
          }
          await this.getProizvod(nextPage);
        } catch (error) {
          alert("Nije uspelo brisanje.");
        }
      }

    searchValueInputChange(event) {
        let control = event.target;
    
        let name = control.name;
        let value = control.value;
    
        let search = this.state.search;
        search[name] = value;
    
        this.setState({ search: search });
      }

      doSearch() {
        this.getProizvod(0);
      }

    render() {
        return (
            <div>
                        <Form.Group style={{marginTop:35}}>
                        <Form.Check type="checkbox" label="Show search form" onClick={(event) => this.setState({showSearch: event.target.checked})}/>
                        </Form.Group>
                        <Collapse in={this.state.showSearch}>
                        <Form style={{marginTop:10}}>
                        <Form.Group>
                            <Form.Label>MinCena</Form.Label>
                            <Form.Control
                            value={this.state.search.minCena}
                            name="minCena"
                            as="input"
                            onChange={(e) => this.searchValueInputChange(e)}
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>MaxCena</Form.Label>
                            <Form.Control
                            value={this.state.search.maxCena}
                            name="maxCena"
                            as="input"
                            onChange={(e) => this.searchValueInputChange(e)}
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Kategorija</Form.Label>
                            <Form.Control
                            onChange={(event) => this.searchValueInputChange(event)}
                            name="kategorija"
                            value={this.state.search.kategorija}
                            as="select"
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
                        <Button onClick={() => this.doSearch()}>Search</Button>
                        </Form>
                        </Collapse>
                <Table striped style={{marginTop: 5}}>
                        <thead className="thead-dark">
                            <tr>
                                <th>Naziv</th>
                                <th>Cena</th>
                                <th>Stanje</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.proizvod.map((proizvod) => {
                                return (
                                <tr key={proizvod.id}>
                                    <td>{proizvod.naziv}</td>
                                    <td>{proizvod.cena}</td>
                                    <td>{proizvod.stanje}</td>
                                    <td>  
                                     <Form.Control
                                        onChange = {(event) => this.kolicinaChange(proizvod.id, event)}
                                        name = "kolicina"
                                        as = "input"
                                        value = {proizvod.kolicina}
                                        type = "number"
                                        placeholder = "Kolicina"
                                     ></Form.Control>
                                    </td>
                                    <Button 
                                    onClick={() => this.kupi(proizvod.id)} 
                                    style={{ marginLeft: 5 }}
                                    disabled = {proizvod.stanje == 0}
                                    >KUPI</Button>
                                    {window.localStorage['role']=="ROLE_ADMIN"?
                                    <Button
                                     style={{ marginLeft: 5}} 
                                     onClick={() => this.doDelete(proizvod.id)}
                                     variant="danger"
                                    >OBRISI</Button>:null}
                                </tr>
                                );
                            })}
                        </tbody>
                    </Table>
                    <ButtonGroup style={{ marginTop: 10, float:"left"}}>
                            <Button 
                            style={{ margin: 3}}
                            disabled={this.state.pageNo==0} onClick={()=>this.getProizvod(this.state.pageNo-1)}>
                            Prethodna
                            </Button>
                            <Button
                            style={{ margin: 3}}
                            disabled={this.state.page==this.state.TotalPages-1} onClick={()=>this.getProizvod(this.state.pageNo+1)}>
                            Next
                        </Button>
                    </ButtonGroup>
            </div>
        )
    }
}

export default withNavigation(Kupovina);