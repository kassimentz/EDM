//
//  ViewController.swift
//  TestesSwift
//
//  Created by iossenac on 10/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate , UITableViewDataSource {

    @IBOutlet var tblExample : UITableView!
    var listElements : NSMutableArray = NSMutableArray()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        tblExample.delegate = self
        tblExample.dataSource = self
        
        tblExample.registerClass(UITableViewCell.classForCoder(), forCellReuseIdentifier: "cellID")
        
        listElements.addObject("elemento 1")
        listElements.addObject("elemento 5")
        listElements.addObject("elemento 3")
        listElements.addObject("elemento 6")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listElements.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell : UITableViewCell = tableView.dequeueReusableCellWithIdentifier("cellID", forIndexPath: indexPath) 
        
        let elementString: String = listElements[indexPath.row] as! String
        
        cell.textLabel!.text = elementString
        
        return cell
    }
}

