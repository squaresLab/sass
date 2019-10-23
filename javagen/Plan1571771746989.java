public class Plan1571771746989 extends Plan { 
public static void main(String[] args) { 

StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {

}

}


for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}

} else {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseDimmer("B");
}

}

}



}
}
