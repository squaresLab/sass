public class Plan1571768711104 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
StartServer("A");
StartServer("A");

}

}


}
}
