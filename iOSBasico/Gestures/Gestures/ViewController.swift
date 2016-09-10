//
//  ViewController.swift
//  Gestures
//
//  Created by iossenac on 10/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBOutlet weak var Label: UILabel!

    @IBAction func ActionRotate(sender: UIRotationGestureRecognizer) {
        let rotation = sender.rotation
        let velocity = sender.velocity
        let text = "rotacao: \(rotation) velocidade: \(velocity)"
        Label.text = text
    }
    
    @IBAction func ActionPinch(sender: UIPinchGestureRecognizer) {
        let scale = sender.scale
        let velocity = sender.velocity
        let text = "pinch scale: \(scale) e velocidade \(velocity)"
        Label.text = text
    }
}

