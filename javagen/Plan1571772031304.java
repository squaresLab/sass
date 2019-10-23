public class Plan1571772031304 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}

} else {
StartServer("B");
}

}


}
}
