public class Plan1571773616247 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}


DecreaseDimmer("C");

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


}

if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}

if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}


StartServer("B");


}
}
