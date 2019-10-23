public class Plan1571774737082 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
}

StartServer("B");

}


}
}
