//
//  CalculatorBrain.swift
//  Calculadora
//
//  Created by iossenac on 03/09/16.
//  Copyright © 2016 Kassiane Mentz. All rights reserved.
//

import Foundation

class CalculatorBrain
{
    var accumulator = 0.0
    
    func setOperand(operand: Double){
        self.accumulator = operand
    }
    
    enum Operation {
        case Constant(Double)
        case UnaryOperation((Double)->Double)
        case BinaryOperation((Double, Double)->Double)
        case Equals
        case Clear
    }
    
    
    func selectOperation(operation: String) -> Operation{
        switch operation {
        case "π": return Operation.Constant(M_PI)
        case "√": return Operation.UnaryOperation(sqrt)
        case "cos": return Operation.UnaryOperation(cos)
        case "sen": return Operation.UnaryOperation(sin)
        case "×": return Operation.BinaryOperation({$0 * $1})
        case "÷": return Operation.BinaryOperation({$0 / $1})
        case "+": return Operation.BinaryOperation({$0 + $1})
        case "-": return Operation.BinaryOperation({$0 - $1})
        case "=": return Operation.Equals
        case "clr": return Operation.Clear
        default: return Operation.Equals
        }
    }
    
    struct BinaryOperationInfo {
        var operand: Double
        var operation: (Double, Double)->Double
        
        func exec(rhs: Double) -> Double {
            return operation(operand, rhs)
        }
    }
    
    var binaryOperationInfo: BinaryOperationInfo?
    
    func execute(operation: String){
        switch self.selectOperation(operation) {
        case .Constant(let value): accumulator = value
        case .UnaryOperation(let function): accumulator = function(accumulator)
        case .BinaryOperation(let function):
            binaryOperationInfo = BinaryOperationInfo(operand: accumulator, operation: function)
        case .Equals: if let op = binaryOperationInfo{
                accumulator = op.exec(accumulator)
            }
        case .Clear:
            accumulator = 0
            binaryOperationInfo = nil
        }
    }
}