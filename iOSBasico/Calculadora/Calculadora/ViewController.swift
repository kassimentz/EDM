//
//  ViewController.swift
//  Calculadora
//
//  Created by iossenac on 03/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var brain  = CalculatorBrain()
    var userStartedToType: Bool = false
    
    @IBOutlet weak var display: UILabel!

    @IBAction
    func digitPressed(_ sender: UIButton) {
        
        let digit = sender.currentTitle!
        
        if userStartedToType{
            display.text = display.text! + digit
        }else{
            display.text = digit
        }
        userStartedToType = true
    }
    
    var displayText: Double {
        get {
            return Double(display.text!)!
        }
        set{
            display.text = String(newValue)
        }
    }

    @IBAction func executeOperation(_ sender: UIButton) {
        
        let operation = sender.currentTitle!
        
        if let value = Double(display.text!){
            brain.setOperand(value)
            brain.execute(operation)
            displayText = brain.accumulator
        }
        
        userStartedToType = false
    }

}

