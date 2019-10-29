public class Plan1571771888926 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

}

} else {
IncreaseTraffic("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("A");
}


}



}
}
