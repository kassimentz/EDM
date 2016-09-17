//
//  EspecieDetailViewController.swift
//  BeMyPet
//
//  Created by iossenac on 17/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import UIKit

class EspecieDetailViewController: UIViewController {

    var especie: String? {
        //depois de setar o valor
        didSet {
            updateUI()
        }
    }
    
    @IBOutlet weak var especieLabel: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        updateUI()
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func updateUI(){
        
        especieLabel?.text = especie ?? ""
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
