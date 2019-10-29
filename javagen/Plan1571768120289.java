public class Plan1571768120289 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

if ( StartServer("A") ) {
StartServer("B");
} else {
IncreaseTraffic("B");
}


for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


StartServer("A");

} else {
DecreaseDimmer("C");
}

}

}
}
