import React ,{Component} from 'react';
import ReactDOM  from 'react-dom';
class Welcome extends Component{
  constructor(props){
    super(props);
    this.state={
      //baseUrl:"http://0.0.0.0:8080/",
      baseUrl:"/SapientFeeCalculator/",
      uploadFilReq:{file:null},
      uploadFile:null,
      uploadRespose:[],
      fileSizeErr:"",

    };
this.handleChooseFile=this.handleChooseFile.bind(this);
this.uploadFile=this.uploadFile.bind(this);

  }
  //handleChooseFile
  handleChooseFile(event){
      const file = event.target.files[0];
      let checkfile=file.name;
      //alert("File Ext. is :"+checkfile.substr(checkfile.lastIndexOf('.')));
       if(checkfile.substr(checkfile.lastIndexOf('.'))!='.xlsx'){
        this.setState({fileSizeErr:"Please Upload only .xlsx file",uploadFile:null});
        return;

      }else if(checkfile.size==0){
          this.setState({fileSizeErr:"File is empty please upload file with data",uploadFile:null});
          return ;
      }
        this.setState({
          uploadFile:file,
          fileSizeErr:"",
          uploadFilReq:{
            file:file,
          }
        });
      // alert("Data:"+file);
  }
  //uploadFile
  uploadFile()
  {

    let uploadFile=this.state.uploadFile;
    if(uploadFile==null){
      this.setState({fileSizeErr:"Please upload file"});
      return;
    }
  //  alert('Data'+this.state.uploadFilReq.file);
  this.setState({fileSizeErr:""});
    let formData = new FormData();
 formData.append('file', uploadFile);
    console.log("uploadFile"+formData);
    //let uploadFilReq=this.state.uploadFilReq;
    let URLuploadTxnFile=this.state.baseUrl+"uploadTxnFile";
    fetch(URLuploadTxnFile,{
    method: 'POST',
    body:formData,
    headers: {
      'Accept': '*/*',
    }}).then(function(response) {
      return response.json()
    }).then(response => {
      console.log("response"+response);
       this.setState({
         uploadRespose:response,
       });
    })



  }
render(){
let   uploadBox={
   width: "500px",
    height: "300px",
    padding: "15px",
    backgroundColor: "white",
    boxShadow: "10px 10px 5px grey",
    display: "block",
    marginLeft: "30%",
    marginRight:"30%",
    textAlign:"center",

 }
 let welcometext={
   paddingTop: "15px",
   boxShadow: "2px 2px 5px grey",
   color:"green",

 }
 let uploadstyle={
   marginTop:"30px",
   paddingTop:"50px",
 }

 let summaryReport=this.state.uploadRespose.map((item,index)=>{
   return  <tr key={index}>
      <td>{item.clientId}</td>
      <td>{item.prority}</td>
      <td>{item.transactionDate}</td>
      <td>{item.transactionType}</td>
      <td>{item.processingFee}</td>

    </tr>;
 });


return(
  <div>
      <div className="container-fluid ">
          <div className="row text-center">
              <div className="col" style={{backgroundColor:"lavender"}}>
              <h1 style={welcometext}>Welocme To Sapient FeeCalculator</h1>
              </div>
          </div>
          <div className="row text-center  "  >
            <div className="col-md-12 " style={{backgroundColor:"lavender",height:"auto",textAlign:"center",}}>
              <div   style={uploadBox}>
                  <h5 style={{color:"red"}}>Please Upload Transaction File in Valid Format</h5>
                  <h6 style={{color:"red"}}>Only xlsx file Valid</h6>
                  <form enctype="multipart/form-data">
                    <div class="input-group input-file" name="fileupload" style={uploadstyle} >

                      <input   type="file" class="form-control" name="files[]" placeholder='Choose a file...' onChange={this.handleChooseFile}/>
                      <span class="input-group-btn">
                        <button class="btn btn-warning btn-reset" type="button" onClick={this.uploadFile}>Upload</button>
                       </span>

                    </div>
                     <h6 style={{color:"red"}}>{this.state.fileSizeErr}</h6>
                    </form>
                </div>
                <h1>Total Records:{this.state.uploadRespose.length} </h1>
                <table class="table table-bordered">
                        <thead>
                          <tr>
                            <th>ClientId</th>
                            <th>Prority</th>
                            <th>TransactionDate</th>
                            <th>TransactionType</th>
                            <th>ProcessingFee</th>
                          </tr>
                        </thead>
                        <tbody>
                        {summaryReport}
                        </tbody>
                  </table>
            </div>
          </div>
    </div>
  </div>
);


}


}

export default Welcome;
