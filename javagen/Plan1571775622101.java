public class Plan1571775622101 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
if ( StartServer("A") ) {
StartServer("B");
} else {
IncreaseTraffic("B");
}


} else {
DecreaseTraffic("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}


}

}
}
