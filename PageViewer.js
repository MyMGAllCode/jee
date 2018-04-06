import React from 'react';
import Pagination from './Pagination';
 
class PageViewer extends React.Component {
    constructor() {
        super();
 
        // an example array of items to be paged
		var redemptionlist =[];
		for(var i=1;i<=150;i++)
		{
			redemptionlist.push({ id: i, name: 'Item ' + i });
		}
       // var exampleItems = _.range(1, 151).map(i => { return { id: i, name: 'Item ' + i }; });
 
        this.state = {
            redemptionlist: redemptionlist,
            pageOfItems: []
        };
 
        this.onChangePage = this.onChangePage.bind(this);
    }
 
    onChangePage(pageOfItems) {
        // update state with new page of items
        this.setState({ pageOfItems: pageOfItems });
    }
 
    render() {
        return (
            <div>
                <div className="container">
                    <div className="text-center">
                        <h1>React - Pagination </h1>
							<table class="table table-bordered">
								<thead >
								  <tr >
									<th className="text-center">Item Id</th>
									<th className="text-center">Item Name</th>
								  </tr>
								</thead>
								<tbody>
								 {this.state.pageOfItems.map(item =>
							   <tr  key={item.id}>
									<td>{item.id}</td>
									<td>{item.name}</td>
								  </tr>
								)}
							   </tbody>
							</table>
                        
                        <Pagination items={this.state.redemptionlist} onChangePage={this.onChangePage} />
                    </div>
                </div>
                <hr />
               
                </div>
            
        );
    }
}
 
export default PageViewer;