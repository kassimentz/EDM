//
//  EspecieTableViewCell.swift
//  BeMyPet
//
//  Created by iossenac on 17/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import UIKit

class EspecieTableViewCell: UITableViewCell {

    @IBOutlet weak var especie: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func configure(especie: String){
        self.especie.text = especie
    }

}
